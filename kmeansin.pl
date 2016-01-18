#!/usr/bin/perl -w
use strict;
$/=":";
my $t=0;
my $line;
my @concept;
my $x=0;
my @temp;
my @temp1;
my @temp2;
open(my $inner,"funny");
while(<$inner>)
{
$line=$_;
$t++;
  if($t>2)
  {
  $line=~s/\n//g;
#print $line;
   if($line=~m/({)(.*)(})/mg)
   {
   $concept[$x]=$2;
   
#print $concept[$x]."\n";
   $x++;
   } 
 }
}
pop(@concept);
my @conceptwords;
my $eachconcept;
my $singleconcept;
my $file;
my $totalfile;
my %weight=();
my $out;
opendir(my $DIR,"/home/deepu/nlp/newcorpus");
my @files=readdir($DIR);
#print @files;
@temp = sort(@files);
#@files = @temp;
shift(@temp);
shift(@temp);
#print @temp;
#print $conceptwords[0]."\n";
my $cc=0;
my $inputfileword;
my @eachfileword=();
foreach $out(@concept)
{
$cc++;
#print "\@attribute"." ".$cc." "."real"."\n";
}
foreach $file(@temp)
{
 print $file." ";
  undef($/);
  open(my $filein,"/home/deepu/nlp/newcorpus/$file");
   while(<$filein>)
   {
   $totalfile=$_;
    }
#print $totalfile;
#last;
    foreach $eachconcept(@concept)
    {
    @conceptwords=split/,/,$eachconcept;
    $weight{$file}{$eachconcept}=0;
       foreach $singleconcept(@conceptwords)
       {

        @eachfileword=split/\s+/,$totalfile;
         foreach $inputfileword(@eachfileword)
         {
         if($inputfileword=~m/$singleconcept/ig)
  	  {
          $weight{$file}{$eachconcept}++;
          }
         }
        }        
print $weight{$file}{$eachconcept}." ";

}
print "\n";
}
