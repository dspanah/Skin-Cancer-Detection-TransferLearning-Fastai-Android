from __future__ import division, print_function
import sys
import os
import glob
import re
from pathlib import Path
from io import BytesIO
import base64
import requests

# Import fast.ai Library
from fastai import *
from fastai.vision import *

# Flask utils
from flask import Flask, request, jsonify


# Define a flask app
app = Flask(__name__)
NAME_OF_FILE = 'model_best'  # Name of the model pth file
PATH_TO_MODELS_DIR = Path('')  # by default just use /models in root dir
classes = ['Actinic keratoses', 'Basal cell carcinoma', 'Benign keratosis',
           'Dermatofibroma', 'Melanocytic nevi', 'Melanoma', 'Vascular lesions']

def setup_model_pth(path_to_pth_file, learner_name_to_load, classes):
    data = ImageDataBunch.single_from_classes(
        path_to_pth_file, classes, ds_tfms=get_transforms(), size=224).normalize(imagenet_stats)
    learn = cnn_learner(data, models.densenet169, model_dir='models')
    learn.load(learner_name_to_load, device=torch.device('cpu'))
    return learn

learn = setup_model_pth(PATH_TO_MODELS_DIR, NAME_OF_FILE, classes)

def decode(img_b64):
    img = base64.b64decode(img_b64)
    return img

def model_predict(img):
    img = open_image(BytesIO(img))
    pred_class, pred_idx, outputs = learn.predict(img)
    print(pred_class)
    formatted_outputs = ["{:.1f}".format(value) for value in [
        x * 100 for x in torch.nn.functional.softmax(outputs, dim=0)]]
    pred_probs = sorted(
        zip(learn.data.classes, map(str, formatted_outputs)),
        key=lambda p: p[1],
        reverse=True
    )
    pred_dict = {i[0]: i[1] for i in pred_probs}
    #for k, v in pred_dict.items():
    #    print(k, v)

    message = {
        'status': 200,
        'message': 'OK',
        'predictions': pred_dict,    
        }
    return jsonify(message)

@app.route('/predict', methods=["POST", "GET"])
def upload():
    if request.method == 'POST':
        # Get the file from post request
        img_b64 = request.form.get("image")
        if img_b64 != None:
            # Make prediction
            img = decode(img_b64)
            preds = model_predict(img)
            return preds
  

if __name__ == '__main__':
    port = os.environ.get('PORT', 8008)

    if "prepare" not in sys.argv:
        app.run(debug=False, host='0.0.0.0', port=port)
