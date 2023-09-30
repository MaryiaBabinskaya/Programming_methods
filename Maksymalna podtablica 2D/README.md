##### <a name="Headers"></a>  Opis
Dla danej niepustej tablicy dwuwymiarowej liczb całkowitych: a[0][0], … ,a[n-1][m-1]
dla 0 ≤ i ≤ j < n, 0 ≤ k ≤ l < m definiujemy jej maksymalną podtablicę jako spójny fragment
a[i..j][k..l] o maksymalnej nieujemnej sumie elementów s(i, j, k, l), obliczanej według wzoru: \
 s(i, j, k, l) = sumę elementów a[x][y] podtablicy, dla których i ≤ x ≤ j oraz k ≤ y ≤ l. \
W przypadku, gdy elementy tablicy są mniejsze od zera, maksymalna podtablica jest pusta
 i s(i, j, k, l) = 0.
 
Napisz w Javie program działający w czasie O( (max(n, m))3 ), który oblicza maksymalną wartość
s(i, j, k, l) oraz wyznacza maksymalną podtablicę mst = a[i..j][k..l] o najmniejszej liczbie elementów,
której indeksy i, j, k, l tworzą ciąg leksykograficznie najmniejszy. 
