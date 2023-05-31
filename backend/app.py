import matplotlib
import cv2
import json
import keras
import imghdr
import os 
import numpy as np
import tensorflow as tf
from flask import Flask,request,jsonify
from matplotlib import pyplot as plt
from PIL import Image

app = Flask(__name__)

# Home Page
@app.route("/", methods=["GET"])
def hello():
    return "<h1>Welcome!</h1>",200

# Model Load


@app.route("/predict", methods=["POST"])
def predict():
    





if __name__ == "__main__":
    app.run(debug=True)