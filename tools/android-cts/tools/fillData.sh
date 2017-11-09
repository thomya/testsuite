#!/system/bin/sh

echo "fill data program ."
echo $0 $1
if [ -z $1 ];then
	exit
fi

size=$1
lastSize=$(($size % 262144))
GS=$(($size / 262144))

function G1_write (){
    echo "G1_write begin ."
    dd if=/dev/zero of=/data/tmp$1.txt bs=4096 count=262144
    echo "G1_write success ."
}

echo " fill begin , size : " $size "; lastSize : " $lastSize "; GS : " $GS
dd if=/dev/zero of=/data/tmp1.txt bs=4096 count=$lastSize
i=1
while [ $i -lt $((GS+1)) ]
do 
   ((i++))
   G1_write $i 
done
echo " fill done . "


