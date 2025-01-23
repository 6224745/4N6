fun main() {
   val a = 5 + 2 * 2
   for (i in 1..2) {
      for (j in 4..6) {
         println((i + j).toString() + (i.toString() + " " + a))
      }
   }
}

| ligne exécutée                                         | effet                                              |
|--------------------------------------------------------|----------------------------------------------------|
| val a = 5 + 2 * 2                                      | a: 9                                               |
| for (i in 1..2)                                        | i parcourt l'interval 1, 2                         |
| for (j in 4..6)                                        | j parcourt l'interval 4, 5, 6                      |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 1 <br /> j: 4 <br /> a: 9 <br /> Affiche "51 9" |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 1 <br /> j: 5 <br /> a: 9 <br /> Affiche "61 9" |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 1 <br /> j: 6 <br /> a: 9 <br /> Affiche "71 9" |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 2 <br /> j: 4 <br /> a: 9 <br /> Affiche "62 9" |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 2 <br /> j: 5 <br /> a: 9 <br /> Affiche "72 9" |
| println((i + j).toString() + (i.toString() + " " + a)) | i: 2 <br /> j: 6 <br /> a: 9 <br /> Affiche "82 9" |
