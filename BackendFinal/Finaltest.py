import matplotlib
import cv2
import json
import keras
import imghdr
import os 
import h5py
import numpy as np
import tensorflow as tf
from flask import Flask, request, jsonify
from matplotlib import pyplot as plt
from PIL import Image
from keras.models import load_model
import tensorflow as tf
import mysql.connector
from flask import Flask, request, jsonify
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)

# Set the upload folder
UPLOAD_FOLDER = 'uploads'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

# Connect to MySQL database
db = mysql.connector.connect(
    host='34.101.57.203',
    user='root',
    password='123',
    database='CSV_DB'
)

# Load the model
model_path = 'model_v1.3.h5'
model = tf.keras.models.load_model(model_path, compile=False)

# Define allowed extensions for image files
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}

# Function to check if the file has an allowed extension
def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route("/", methods=["GET"])
def hello():
    return "<h1>Welcome!</h1>", 200

@app.route("/predict", methods=["POST"])
def predict():
    try:
        class_labels = ['Air Conditioner', 'Hair Dryer', 'Iron', 'Lamp','Laptop', 'Oven', 'Refrigerator', 'Rice Cooker','Television', 'Vacuum Cleaner', 'Washing Machine']
        # Check if the POST request contains a file part
        if 'file' not in request.files:
            return jsonify({"error": "No file part in the request."})

        file = request.files['file']
        
        # Check if a file was uploaded and has an allowed extension
        if file.filename == '':
            return jsonify({"error": "No file selected."})
        if not allowed_file(file.filename):
            return jsonify({"error": "Invalid file type. Only PNG and JPEG images are allowed."})

        # Save the uploaded file to the upload folder
        filename = secure_filename(file.filename)
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))

        # Load the uploaded image and preprocess it
        img = tf.keras.preprocessing.image.load_img(os.path.join(app.config['UPLOAD_FOLDER'], filename), target_size=(224, 224))
        img_array = tf.keras.preprocessing.image.img_to_array(img)
        img_array = tf.expand_dims(img_array, axis=0)
        img_array /= 255.

        # Make predictions with the model
        predictions = model.predict(img_array)
        predicted_class_index = np.argmax(predictions[0])
        predicted_class = class_labels[predicted_class_index]
        confidence = predictions[0][predicted_class_index] * 100
        result = "This image most likely belongs to {} with a {:.2f} percent confidence.".format(predicted_class, confidence)


        # Perform database operations
        cursor = db.cursor()
        # Query the database based on the prediction result
        sql_query = "SELECT * FROM Main WHERE Object_Name = %s"
        cursor.execute(sql_query, (predicted_class,))  # Pass the predicted class as a parameter
        item = cursor.fetchone()  # Retrieve the first matching item



        if item:
            item_info = {
                "name": item[0],
                "Image": item[1],
                "Dampak Produksi": item[3],
                "Dampak Konsumsi": item[4],
                "Dampak Disposal": item[5],
                "Link Sumber": item[6],
                "Average Energy": item[7],
                # Add more fields as needed
            }
            return jsonify({"result": result, "item": item_info})
        else:
            return jsonify({"result": result, "item": None})

    except Exception as e:
        return jsonify({"error": str(e)})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080)

