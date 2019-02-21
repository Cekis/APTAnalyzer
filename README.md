#APT Analyzer

##Usage
You probably don't want to run this directly.  You can, but most settings were hard coded instead of being arguments (i.e. set to my E: drive).  That being said, feel free to fork and modify how you see fit.  Most of the value is in the libraries in how they read the APT files and which tags they actually pull out.  The library files (APT, LOD, MSH, etc) are NOT complete... they do NOT read all possible tags at this time.  They only read the tags I wanted them to read for the purpose this thing was made for - creating server side SSA files.  You can safely assume any tags this thing does NOT read were NOT important to this purpose.
