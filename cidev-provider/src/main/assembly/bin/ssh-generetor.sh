#! /bin/sh
if [ ! -d ~/.ssh/$1 ]; then 
mkdir -p ~/.ssh/$1
echo "--------------------------mkdir success---------------------------------"
fi
ssh-keygen -f ~/.ssh/$1/id_rsa -t rsa -q -N ""
echo "--------------------------create sshkey success-------------------------"
exit
