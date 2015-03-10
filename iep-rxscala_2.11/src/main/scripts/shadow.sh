#!/bin/bash

jar=$1
dir=$2

pwd=`pwd`

echo jar: ${pwd}/${jar}, dir: ${dir}

mkdir -p $dir

cd $dir
jar xf "${pwd}/${jar}"

for f in `find . -type f`; do echo $f; sed -i '.orig' 's/rx\./iep.rx./g;' $f; done

cd ${pwd}
./project/sbt test package
