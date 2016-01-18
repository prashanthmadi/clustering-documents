opendir(DIR,"/home/naveen/Desktop/fcorpus/") or die "cant open dir";
@files=readdir(DIR);
undef($/);
foreach $i(@files)
{
open(file,"/home/naveen/Desktop/fcorpus/$i");
$x=<file>;
@y=split(/\n/,$x);
=pod
$t=0;
foreach $i(@y)
{
$t++;
if($i=~m/^\s*$/g)
{
last;
}
}
@slice=@y[$t..$#y];
foreach $i(@slice)
=cut
foreach $i(@y)
{
$i=~s/<(.*)@(.*)>//g;
print $i."\n";
}
}
