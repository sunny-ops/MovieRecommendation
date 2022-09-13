import numpy as np
import pandas as pd
pd.set_option('display.max_columns', None)
pd.set_option('display.width', 500)
pd.set_option('display.expand_frame_repr', False)
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

df = pd.read_csv('../input/the-movies-dataset/movies_metadata.csv', low_memory=False)

print(df['overview'].isnull().sum())
#overview degiskeninde eksik degerler var. Bunlari bos stringle dolduruyoruz.
df['overview'] = df['overview'].fillna('')

tfidf = TfidfVectorizer(stop_words='english')
tfidf_matrix = tfidf.fit_transform(df['overview'])
tfidf_matrix = tfidf_matrix.astype(np.float32)
cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)

# Filmlerin isimlerini indexlere koyduk. Column olarak da indexleri koyduk.
indices = pd.Series(df.index, index=df['title'])
# Bazi filmler birden fazla satirda gozlemleniyor bundan dolayi bunlari cikardik.
indices = indices[~indices.index.duplicated(keep='last')]

# Matrix filmi icin en benzer 10 film
cosine_similarity = pd.DataFrame(cosine_sim[indices['The Matrix']], columns=['score'])
movie_indices = cosine_similarity.sort_values(by='score', ascending=False)[1:11].index
df['title'].iloc[movie_indices]

def content_based_recommender(title, cosine_sim, dataframe):
  # title - index olusturma
  indices = pd.Series(dataframe.index, index=dataframe['title'])
  indices = indices[~indices.index.duplicated(keep='last')]
  # title'in indexini yakalama
  movie_index = indices[title]
  # title'a gore benzerlik skorlari hesaplama
  similarity_scores = pd.DataFrame(cosine_sim[movie_index], columns=['score'])
  # ilk 10 benzer filmi getirme
  movie_indices = similarity_scores.sort_values(by='score', ascending=False)[1:11].index
  return dataframe['title'].iloc[movie_indices]


def calculate_cosine_sim(dataframe):
  # tidf instance'i olusturma
  tfidf = TfidfVectorizer(stop_word='english')
  # dataframe'i hazirlama
  dataframe['overview'] = dataframe['overview'].fillna('')
  # tidf_matrix olusturma
  tfidf_matrix = tfidf.fit_transform(dataframe['overview'])
  tfidf_matrix = tfidf_matrix.astype(np.float32)
  # cosine_sim matrisi olusturma
  cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
  return cosine_sim