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

**Testing Dataset** 

read in the model *either one you trained from the previous step or ours (models/200dp.pkle)* using pickle
2) instantiate a environment object from myEnv.py
3) run the following  (this will test the spmn against one simulation, and you can loop the while loop to continue it for however many iterations you wish we performed 600 on our model.  
```python
#env (the instantiated environment)
#spmn (the read in model)

from spn.algorithms.MEU import best_next_decision

state = env.reset()
isDone = False
states = []
utilities = []
while(!isDone):
    state, utility, isDone = env.step( best_next_decision(spmn, state)
    states.append(state)
    utilities.append(utility)
    
print(states)
print(utilities)
```
