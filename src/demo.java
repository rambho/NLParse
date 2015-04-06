/**
 * Created by ram on 4/4/2015.
 */
public class demo {
    public static void main(String[] args) {
        try {
            NLP language = new NLP();
            language.process("A Natural Language Processing helper library for research purposes.");
            language.printAll();
            /*
            POS_PARSER parser = new POS_PARSER();
            parser.parse("(ROOT\n" +
                "  (S\n" +
                "    (VP (VB Example)\n" +
                "      (NP (NN problem))\n" +
                "      (SBAR (IN as)\n" +
                "        (S\n" +
                "          (VP (VBN presented)\n" +
                "            (PP (IN in)\n" +
                "              (NP (PRP$ my) (NNP IBM) (NNP Watson) (NNP Interview)))))))\n" +
                "    (. .)))");
                    */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
/*
Sample Output:

        TAGGED_SENTENCE:


        PART_OF_SPEECH_TAGGING:
        (ROOT
        (NP
        (NP (DT A) (NNP Natural) (NNP Language) (NNP Processing) (NN helper) (NN library))
        (PP (IN for)
        (NP (NN research) (NNS purposes)))
        (. .)))

        TYPE_DEPENDENCY:
        et(library-6, A-1)
        nn(library-6, Natural-2)
        nn(library-6, Language-3)
        nn(library-6, Processing-4)
        nn(library-6, helper-5)
        root(ROOT-0, library-6)
        prep(library-6, for-7)
        nn(purposes-9, research-8)
        pobj(for-7, purposes-9)

        TYPE_DEPENDENCY_COLLAPSED:
        det(library-6, A-1)
        nn(library-6, Natural-2)
        nn(library-6, Language-3)
        nn(library-6, Processing-4)
        nn(library-6, helper-5)
        root(ROOT-0, library-6)
        nn(purposes-9, research-8)
        prep_for(library-6, purposes-9)

        FLATTENED_PARSE_TREE:
        [[ ROOT:  NP:  NP:  DT:  <A> ] [ NNP:  <Natural> ] [ NNP:  <Language> ] [ NNP:  <Processing> ] [ NN:  <helper> ] [ NN:  <library> ] ] [ PP:  IN:  <for> ] [ NP:  NN:  <research> ] [ NNS:  <purposes> ] ] ] [ .:  <.> ] ] ] [ [ [ [ []
*/
