import os
path = "/home/naveen/Desktop/tcorpus/1/"
outputpath = "/home/naveen/Desktop/tcorpus/2/"
for (path, dirs, files) in os.walk(path):
   for f in files:
      filepath = path + f
      outputfilepath = outputpath + f  
      file = open(filepath,"r")
      outputfile = open(outputfilepath,"a")  
      for line in file:
          line = line.strip("|")
          line = line.strip(" ")
          list = line.split("|")
          for element in list:
             
             wordlist = element.strip().split(" ")
             for fullword in wordlist:
                 checklist = fullword.split("/")
                 if len(checklist) < 3:
                      continue
                 elif checklist[2].startswith("B-NP") and not checklist[1].startswith("CD") and not checklist[1].startswith("V") and not checklist[1].startswith("LS") and not checklist[1].startswith("D") and not checklist[1].startswith("PRP") and not splittemp[1].startswith("POS") and not checklist[1].startswith("RB") and not checklist[1].startswith("SYM"):
                        
                            outputfile.write(element)
                            outputfile.write(" | ");
                            break 
                 elif checklist[2].startswith("B-NP"):
                      for array in range(0,len(wordlist)):
                           splittemp = wordlist[array].strip().split("/")
                           if len(splittemp) <= 1 or splittemp[1].startswith("CD") or splittemp[1].startswith("V") or splittemp[1].startswith("LS") or splittemp[1].startswith("POS") or splittemp[1].startswith("RB") or splittemp[1].startswith("D") or splittemp[1].startswith("PRP") or splittemp[1].startswith("UH") or splittemp[1].startswith("SYM"):
                             continue
                           else:
                             outputfile.write(wordlist[array])
                             outputfile.write(" ")
                      outputfile.write(" | ")
                      break
                 elif checklist[1].startswith("JJ"):
                       outputfile.write(fullword)
                       outputfile.write( " | ")
                 elif checklist[1].startswith("N"):
                       outputfile.write(fullword)
                       outputfile.write(" | ")                 
          outputfile.write("\n")  
