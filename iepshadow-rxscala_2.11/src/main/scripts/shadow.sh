#!/bin/bash

jar=$1
dir=$2

pwd=`pwd`

echo jar: ${pwd}/${jar}, dir: ${dir}

rm -rf $dir
mkdir -p $dir

cd $dir
jar xf "${pwd}/${jar}"

uname=`uname`

if [ "${uname}" == "Darwin" ]; then
  for f in `find . -type f`; do echo $f; sed -i '.orig' 's/rx\./iep.rx./g;' $f; done
else
  for f in `find . -type f`; do echo $f; sed -i 's/rx\./iep.rx./g;' $f; done
fi

cd ${pwd}
./project/sbt package
