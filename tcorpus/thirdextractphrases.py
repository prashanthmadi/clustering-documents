import os
path = "/home/naveen/Desktop/tcorpus/2/"
outputpath = "/home/naveen/Desktop/tcorpus/3/"
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
          print list
          for element in list:
             element = element.lstrip()
             if element is None or element == "\n" or element == "":
                continue
             list1 =element.split(" ")
             for element1 in list1:
                 list2 = element1.split("/")
                 if element1.strip().startswith(",") or element1.strip().startswith("\n") or element1.strip().startswith("'s"):
                     continue 
                 outputfile.write(list2[0].strip())
                 outputfile.write(" ")
             outputfile.write("\n")
