##### <a name="Headers"></a>  Opis
Danych jest n odcinków, których długości są liczbami całkowitymi dodatnimi, umieszczonymi w tablicy T[]. \
Napisz w Javie program, który wyznaczy liczbę możliwych trójek indeksów w tablicy zawierających odcinki, z których można zbudować trójkąt, działający z pesymistyczną złożonością O(n^2log2n). \
Każdy odcinek może występować tylko raz w budowanym trójkącie, choć może być wiele odcinków o tej samej długości. Może się też zdarzyć, ze otrzymamy kilka trójkątów o takich samych długościach boków, w takim przypadku liczymy je wszystkie. \
Przykładowo dla tablicy T[] = [2, 2, 3, 3] można zbudować 4 trójkąty, których długości boków występują w tablicy pod indeksami: \
(0, 1, 2), (0, 1, 3), (0, 2, 3) i (1, 2, 3). \
W komentarzu, w opisie idei rozwiązania uzasadnij złożoność obliczeniową rozwiązania. 
