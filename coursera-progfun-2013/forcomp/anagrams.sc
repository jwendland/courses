import forcomp.Anagrams._

val so = sentenceOccurrences(List("I", "love", "you"))

def words(occs: Occurrences): List[Sentence] =
  for {
    (comb, word) <- combinations(so) map (comb => (comb, dictionaryByOccurrences.withDefaultValue(List())(comb)))
    if word != List()
    rest = subtract(occs, comb)
    _ = println(rest)
  } yield word ++ words(rest).flatten
words(so)




























