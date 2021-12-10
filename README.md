# real_pyramid

To Recreate Results 


**Building Dataset**

1) make a "Output" directory in repo folder
2) `pip install numpy pandas matplotlib javaobj-py3`
3) `python main.py`

Files will be put into the Output directory


**Training Dataset**

*currently not functional outside of a certain UGA vm because of a problem with the independence testing*
*too make it learn outside of this evironment change the RDC independence test inside spmn.py into a naive independence test*
1) download https://github.com/SwarajPawar/SPFlow
2) modify the name of the "datasets" variable to be "pyramid" in learnspmn.py
3) replace the metaData.py file with the one in this repo (found in spn/data/)
4) run learnspmn.py



