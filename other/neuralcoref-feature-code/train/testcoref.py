#import spacy
import en_coref_md
import sys
sys.path.append(r'/home/hung/git/neuralcoref/build/lib.linux-x86_64-2.7/')
sys.path.append(r'/home/hung/git/neuralcoref/build/temp.linux-x86_64-2.7/')
sys.path.append(r'/home/hung/git/neuralcoref/neuralcoref/train/compat.py')
sys.path.append(r'/home/hung/git/neuralcoref/neuralcoref/train/document.py')
sys.path.append(r'/home/hung/git/neuralcoref/neuralcoref/train/conllparser.py')

print(sys.path)


nlp = en_coref_md.load()
doc = nlp(u'My sister has a dog. She loves him')

doc._.coref_clusters
doc._.coref_clusters[1].mentions
doc._.coref_clusters[1].mentions[-1]
doc._.coref_clusters[1].mentions[-1]._.coref_cluster.main

token = doc[-1]
token._.in_coref
token._.coref_clusters

span = doc[-1:]
span._.is_coref
print(span._.coref_cluster.main)
print(span._.coref_cluster.main._.coref_cluster)
