# Capstone_Team-C23_PR526
This repository was created for the capstone project team C23 PR526

# Members:
## Machine Learning:
1. Muhammad Iqbal Aldeena (M350DSX1794) - Universitas Sumatera Utara
2. Hajarani Syadzwana (M350DSY1435) - Universitas Sumatera Utara
3. Azilla Auri Pramesti (M125DKY4655) - UIN Syarif Hidayatullah Jakarta

## Cloud Computing:
1. T.M. Rezha Taufiqurrahman, Mx (C350DSX1608) - Universitas Sumatera Utara
2. Afdan Syukron (C058DKX4155) - Politeknik Negeri Banyuwangi

## Mobile Development:
1. Putra Cendikia Subekti (A034DKX4179) - Institut Teknologi Kalimantan

# Selected Themes
Sustainable Living

# Title of the Project
EcoScan : Track down Your Energy Usage for a better environment for everyone!

# Summary of the Project
-- INSERT HERE --

# Steps to Replicate the Project

1. Machine Learning
- Gather the required dataset
Collect the dataset from open and public dataset provider (kaggle, roboflow, etc) or scrape images if needed. In this case, we are scrape images of household appliances from Google using Chrome Extension: Download All Images.
After that, we cleaning and resizing the dataset. Only images meet the specified criteria are used by us. After that we split the balanced data per class with a training and validation ratio of 70:30

- Build the model
Before building the model we do image augmentation.To build a model start with install all the libraries required for image classification models, we use Tensorflow and transfer learning model: DenseNet121, of which you can see the architecture at the following link: https://iq.opengenus.org/architecture-of-densenet121/. Then add training again in addition with GlobalAveragePooling2D(), Dense(11, activation='softmax')
Train the model using local Jupyter Notebook.

- Plot the loss and accuracy of training and validation into metrics. 
We can see if this model is either overfitting, underfitting, or suit the dataset well. In this case, the DenseNet121 suits our dataset well with accuracy >96%.

- Save the model
We save the model in .h5 format using command model.save("your_model_name.h5").

- Make predictions
Install all libraries required.
Define the label of our classes (we have 11 classes: Air Conditioner, Hair Dryer, Iron, Laptop, Oven, Rice Cooker, Refrigerator, Television, Vacuum Cleaner, Washing Machine, and Lamp).
Load the image for testing.
Resize and converted the image into array form.
Load the model that we saved before (in step 5).
Make predictions (in our case,the predictions for all classes have a confidence value > 90%).

- Configure and deploy the model

2. Cloud Computing
- Build the Flask Environment on your local computer and editor (in this case we are using Visual Studio Code)
- Install all the libraries required for the application (Both ML and CC)
- Create a Cloud SQL Instance so we can use MySQL and store our data in the database
- Head over to PhpMyAdmin to create the database and all the tables required. in this case we have 6 tables that are connected to each other using Primary key and Foreign Key
- Code the API based on the Machine Learning's Model's Output using route and the methods for it
- Test if the API is correct and give out the correct results by using Postman
- Create a VM instance in GCP using Compute Engine with your prefered requirements.
- Setup Firewalls and all the dependencies you need like location, storage, etc.
- Once the VM is ready, connect using SSH
- Once you in the SSH install Python and all the required libraries in it
- Deploy the API by running the command "python3 your_file_name.py"
- Test the API again using Postman with the External IP of the VM instance
- If the API has been deployed and gives out correct results then its time to hand it to the Mobile Development
- Monitor the API incase there are any errors or bugs

3. Mobile Development
- Design Wireframe
- Design Mockup
- Slicing/Implement our Mockup to UI
- Use Navigation Component for flow of Application
- Implement clean architecture for consuming/Integrating to API with Retrofit2
- Build APK


# Tools and Resources Used in this project 

# Machine Learning
- Libraries (Tensorflow, Numpy, Matplotlib, Glob, os, Pandas, Pillow, PIL, shutil, sklearn)
- Jupyter Notebook
- Anaconda
- Github
- Google Image Scapper (Download All Images)

# Cloud Computing
- Google Cloud (with $200 worth of credit)
- Flask Web Framework
- Postman
- MySQL
- Visual Studio Code
- GitHub

# Mobile Development
- Android Studio
- Kotlin
- Navigation Component
- CameraX
- DaggerHilt
- Retrofit
- OkHttp3
- Room
- Glide

# Datasets
Drive : https://drive.google.com/drive/folders/1Jadk8mU3dPPhifJB0JA1dJF31CPwpNk0?usp=sharing

# App Design 
- UI EcoScan : https://www.figma.com/file/LLsOE1Ggj4tihq5UdGQvD6/Eco-Scan-Mobile-Apps?type=design&node-id=0%3A1&t=8Xi3thiLxTwMTSJD-1 

# Another Complement
- Wireframe : https://www.figma.com/file/j2wr2OdJk7mLKyA7cRilsQ/Application-Design?type=design&node-id=0%3A1&t=w4swHHDKWGRgTYg4-1
- Flowchart Capstone : https://www.figma.com/file/WXZr4PVVRCIcUsqh1mAJ0g/Flow-Chart-Product-Based-Capstone?type=whiteboard&node-id=0%3A1&t=LS06Xi0XCZbhZhnO-1 

# Paper / Journals / Article
1. Hischier, R., Reale, F., Castellani, V., & Sala, S. (2020). Environmental impacts of household appliances in Europe and scenarios for their impact reduction. Journal of Cleaner Production, 267, 121952. https://doi.org/10.1016/j.jclepro.2020.121952
https://www.researchgate.net/publication/341338317_Environmental_impacts_of_household_appliances_in_Europe_and_scenarios_for_their_impact_reduction
2. Huang, G., Liu, Z., van der Maaten, L., & Weinberger, K. Q. (2017). Densely Connected Convolutional Networks. Retrieved from https://arxiv.org/abs/1608.06993
