import java.io.*;
import java.text.*;
import java.util.*;

import edu.northwestern.at.utils.*;
import edu.northwestern.at.utils.corpuslinguistics.lexicon.*;
import edu.northwestern.at.utils.corpuslinguistics.postagger.guesser.*;
import edu.northwestern.at.utils.corpuslinguistics.sentencesplitter.*;
import edu.northwestern.at.utils.corpuslinguistics.tokenizer.*;

public class SentenceAndTokenOffsets
{
    public static void main( String[] args )
    {
        try
        {
            if ( args.length > 0 )
            {
                  File folder = new File(args[0]);
                  String[] listofFiles = folder.list();
                  for(int i = 0;i<listofFiles.length;i++)
                  {
                            displayOffsets("/home/bd0077/Desktop/wikipediaprogs/testset1/"+listofFiles[i] );

                  } 
            }
            else
            {
                System.err.println(
                    "Usage: SentenceAndTokenOffsets inputFileName" );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /** Display sentence and token offsets in text.
     *
     *  @param  inputFileName   Input file name.
     */

    public static void displayOffsets( String inputFileName )
        throws Exception
    {
                            //  Wrap standard output as utf-8.
        String filename = inputFileName+".out";
        System.out.println(filename); 
        BufferedWriter out = new BufferedWriter(new FileWriter(filename, true)); 
         
        PrintStream printOut    = 
            new PrintStream
            (
                new BufferedOutputStream( System.out ) ,
                true ,
                "utf-8"
            );
                                //  Load text to split into
                                //  sentences and tokens.

        String sampleText   =
            FileUtils.readTextFile( inputFileName , "utf-8" );

                                //  Convert all whitespace characters
                                //  into blanks.  (Not necessary,
                                //  but makes the display cleaner below.)

        sampleText  = sampleText.replaceAll( "\\s" , " " );

                                //  Create default sentence splitter.

        SentenceSplitter splitter   = new DefaultSentenceSplitter();

                                //  Create part of speech guesser
                                //  for use by splitter.

        PartOfSpeechGuesser partOfSpeechGuesser = new DefaultPartOfSpeechGuesser();

                                //  Get default word lexicon for
                                //  use by part of speech guesser.

        Lexicon lexicon = new DefaultWordLexicon();

                                //  Set lexicon into guesser.

        partOfSpeechGuesser.setWordLexicon( lexicon );

                                //  Get default suffix lexicon for
                                //  use by part of speech guesser.

        Lexicon suffixLexicon       = new DefaultSuffixLexicon();

                                //  Set suffix lexicon into guesser.

        partOfSpeechGuesser.setSuffixLexicon( suffixLexicon );

                                //  Set guesser into sentence splitter.

        splitter.setPartOfSpeechGuesser( partOfSpeechGuesser );

                                //  Create default word tokenizer.

        WordTokenizer tokenizer = new DefaultWordTokenizer();

                                //  Split input text into sentences
                                //  and words.

        List<List<String>> sentences    =
            splitter.extractSentences
            (
                sampleText ,
                tokenizer
            );
                                //  Get sentence start and end
                                //  offsets in input text.

        int[] sentenceOffsets   =
            splitter.findSentenceOffsets( sampleText , sentences );

                                //  Loop over sentences.

        for ( int i = 0 ; i < sentences.size() ; i++ )
        {
                                //  Get start and end offset of
                                //  sentence text.  Note:  the
                                //  end is the end + 1 since that
                                //  is what substring wants.

            int start       = sentenceOffsets[ i ];
            int end         = sentenceOffsets[ i + 1 ];

                                //  Get sentence text.

            String sentence =
                sampleText.substring( start , end );

                                //  Display sentence number,
                                //  start, end, and text.

                                //  Get word tokens in this sentence.

            out.write(sentence+"\n");
            List words  = sentences.get( i );

                                //  Get offsets for each word token
                                //  relative to this sentence.

            int[] wordOffsets   =
                tokenizer.findWordOffsets( sentence , words  );

                                //  Loop over word tokens.

            for ( int j = 0 ; j < words.size() ; j++ )
            {
                                //  Get start and end offset of
                                //  this word token.  Note:  the
                                //  end is the end + 1 since that
                                //  is what substring wants.

                start   = wordOffsets[ j ];
                end     =
                    wordOffsets[ j ] + words.get( j ).toString().length();

                                //  Display token number,
                                //  start, end, and text.

             //  printOut.println
               // (
                 //  "          " + j + " [" + start + "," +
                  // ( end - 1 ) + "]: " +
                  // sentence.substring( start , end )
              // );
            }
        }
     out.close();
    }
}

