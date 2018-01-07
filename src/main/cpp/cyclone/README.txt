cyclone 0.1 alpha 57 source tar ball README.txt

This package is a transitional package, from the Pd-extended build 
system to the new Pdlibbuilder build system. Its structure differs 
from the svn repository as it only includes the cyclone code. The 
shared directory, containing the framework for cyclone, pddp and 
toxy, is purged from non-cyclone code and placed below cyclone. 
This required changing paths in the Makefile.cyclone and 
shared/shared.h (the latter only for the build_counter file).

The Pdlibbuilder project (https://github.com/pure-data/pd-lib-builder) 
configuration for cyclone builds all hammer and sickle objects as 
separate executables. The exception are the binary operators, these 
are placed in the nettles library object.
The current makefile does not create the hammer and sickle library 
objects, the maxmode object and the cyclist executable. These are 
mainly for importing Max 4.6 patches.

Some useful arguments to make:
pdincludepath=<pure data src directory>, like: /home/fjkraan/pd-0.46-6/src
installpath=<pure data explicit install directory>, like /usr/local/lib/pd-externals/cyclone
pkglibdir=<pure data implicit install directory, like /usr/local/lib/pd-externals (this will install in /usr/local/lib/pd-externals/cyclone)

Fred Jan Kraan
fjkraan@xs4all.nl
2015-09-10


Release notes:
alpha57
* fixed "bits " message for bitand~, bitor~ and bitxor~ (bug #1186, svn r17418),
* fixed crash on "set " message for count~ (svn r17394),
* added "pause", "resume" and "stop" messages and behaviour to Line~ (svn r17398),
* fixed poltocar~ phase polarity (svn r17483),
* fixed rand~ argument (bug #1177),
* changed in arsic/vefl.c the call of garray_getfloatarray to garray_getfloatwords and the calling code in buffir~, cycle~, index~, lookup~, peek~, play~, poke~, record~ and wave~ perform methods (svn r17482, r17393 r17397),
* fixed the file handling in funbuff (bug #1188, svn r17419),
* fixed the seed argument in decide, and made normal behaviour more random,
* updated build_counter to 57,
* updated cyclone-meta.pd,
* updated help-patches and added 'always in sync' output~.pd and dspSwitch.pd (svn r17422, r17471),
* fixed issue in slide~ with incorrect down-slide when specifying a low-value up-slide (svn r17475),
* changed the rampsmooth~ ramp to linear as specified in the documentation (svn r17474),
* checked and improved sah~ help-patch (svn r17473),
* added [separate( to coll object (svn r17418),
* fixed offset argument in cycle~ (svn r17479),
* fixed internal path for comment (bug #215, svn r17480, r17481),
* added max5mode in Bucket, different load behaviour with second argument (svn r17484),
* changed the nettles library object to operate outside the cyclone library object (svn r17485),
* fixed help for the nettles and load with declare (replacing import) (svn r17486),
* added teeth~ abstraction (svn r17488),
* fixed the rampsmooth~ inlets (svn r17490),
* removed the logpost(version) at 'all' level to improve < 0.43 compatibility (svn r17591),
* added new build system files (expected to replace the native build system) (svn r17492).
* Added examples to cyclone cartopol~ and poltocar~ help patches (svn r17493),
* Fixed [lookup~] behaviour (svn r17495).

