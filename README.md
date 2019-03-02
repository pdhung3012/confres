# confres

Rule-based Feature Extraction and Applying Machine Learning Models for Conference Resolution from the WikiCoref corpus

Author: Hung Phan and Ziwei Zhou


In this project, we design an approach for building a training data and testing data from a wellknown corpus WikiCoref, then apply these data on machine learning models for mention co-refered prediction. Our machhine learning will give a document in CONLL form as input, and the output is the set of mentions and the result of co-reference result (1 for co-refer and 0 otherwise for each pair of mentions in the document. We conduct the data which have a set of mentions in 30 documents of WikiCoref, including with feature vector for each pair of mentions and the label for the co-reference result. 
The corpus is over 1.6 millions pairs of mentions in WikiCoref. We apply several machine learning approaches and do the cross-validation for each mentions and have the accuracy as follow;

- GaussianNB (55.67% in total accuracy)
- LogisticRegression (92.16%)
- DecisionTreeClassifier (92.47%)
- RandomForestClassifier (92.65%)
- AdaBoostClassifier (to be updated)
- LinearDiscriminantAnalysis (57.29%)
- QuadraticDiscriminantAnalysis()
- LinearSVC
- NuSVC 
- MLPClassifier
- GradientBoostingClassifier

The process of implementing this project is done by 3 steps:

Step 1: Generate Conll format from Ontonote file of WikiCoref:
- Since WikiCoref doesn't have manualled labeling information like Conll dataset, we use Stanford NLP Parser to get parsed tree information and semantic labeling information for each word in WikiCoref.

Step 2: Extracting feature from WikiCoref in Conll format:
- We extract the feature for each mentions and other features relate to pairs of mentions (such as mention distance, mention spans) to get a 70-dimention of vector for each pair of mentions.
- We use Spacy library and improve the code from NeuralCoref (https://github.com/huggingface/neuralcoref) to handling feature for WikiCoref.

Step 3: Writing code for applying Machine Learning models predicting mention co-referred.
- We write code in python that take all feature and label data in csv format and get the total accuracy by cross validation.
- If the evaluation is time consumming, you can use our compact version of training-testing data.

In overal, in this project we build Machine Learning models that given 2 arbitrary mentions and produce the output that predict 2 mentions are co-referred or not. We think that the 2 remaining challenges of this corpus is automatically generated label and the corpus contains many pairs with 0 label so it might un-balance.The details of each steps can be seen in the documentation folder.






