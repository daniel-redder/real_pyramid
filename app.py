import pandas as pd
import numpy as np
from flask import Flask, render_template, request, redirect, url_for, flash, json
import os.path
import datetime
import json as js
import os




app = Flask(__name__,template_folder='templates')


@app.route('/')
def home_builder():

	return render_template("home.html")


#app.run(debug=False) for django later
app.run(host='0.0.0.0',port=8080)