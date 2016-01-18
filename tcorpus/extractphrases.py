import os
path = "/home/naveen/Desktop/tcorpus/x/"
outputpath="/home/naveen/Desktop/tcorpus/1/"
for (path, dirs, files) in os.walk(path):
   for f in files:
      fileoutputpath = outputpath + f
      filepath = path + f
      file = open(filepath,"r")
      filewrite = open(fileoutputpath,"a") 
      for line in file:
          
          line = line.strip() 
          list = line.split(" ")
          for i in list:
             list1 = i.split("/")
             if list1[2].startswith("O"):
                continue
             if list1[2].startswith("B"):
                filewrite.write(" | ")
                filewrite.write(i)
                filewrite.write(" ")
             else:
                filewrite.write(i)
                filewrite.write(" ")
          filewrite.write("\n")  

