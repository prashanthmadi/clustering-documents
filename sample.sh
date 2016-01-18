for i in /home/bd0077/Desktop/wikipediaprogs/testset1/*.out; do
     if [ -f $i ]; then
     # if the file is there
        java -mx512M -classpath $CLASSPATH crf.tagger.CRFTagger -modeldir /home/bd0077/Desktop/wikipediaprogs/CRFTagger/model/ -inputfile $i
        echo $i
        java -mx512M -classpath $CLASSPATH crf.chunker.CRFChunker -modeldir /home/bd0077/Desktop/wikipediaprogs/CRFChunker/model/ -inputfile $i.pos

     fi
  done
