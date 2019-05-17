## Overview

This is the source code for a deep learning based skin cancer detection android app. The model has been built using **fastai** deep learning library which is a high level api for pytorch. A **flask API** has also been implemented for **cloud-based inference**. The classifier has been trained and validated on [Kaggle MNIST HAM10000 dataset](https://www.kaggle.com/kmader/skin-cancer-mnist-ham10000) which contains 10015 images of seven categories of pigmented skin lesions.  As a preprocessing step, I have applied random undersampling to data to alleviate the class-imbalance problem. The classifier has been trained with transfer learning technique using a pretrained **Densenet169** model. The final classifer achieved an accuracy of **91.2%** and a F1-score of **91.7%** on validation data. You can check out the jupyter notebook that goes along to follow all the steps which have been taken to build the model.

## Screenshots
<img src="https://user-images.githubusercontent.com/34622266/57833782-1a521280-77d0-11e9-917f-56db245998a4.jpg" width="480" height="854"> <img src="https://user-images.githubusercontent.com/34622266/57833790-1d4d0300-77d0-11e9-95e7-5524e760c9dd.jpg" width="480" height="854">


## Dependencies

- Python 3.6 <br/>
- Fastai 1.0.52 <br/>
- Flask <br/>
- Gunicorn <br/>
- [SquareCamera](https://github.com/boxme/SquareCamera) <br/>
- [Volley](https://github.com/google/volley)

## Instructions
In order to setup flask API first run `sudo pip install -r requirements.txt`  to install the required dependencies. Then launch the app by running `python app.py`. When you take a photo of skin lesion using the android app, a base64 encoding of the image will be sent to the API at http://localhost:8008. Once you set up the API, download [Android Studio](https://developer.android.com/studio) and then import the android app. Since I've placed the dependencies in the build.gradle file, they should be automatically downloaded. 
