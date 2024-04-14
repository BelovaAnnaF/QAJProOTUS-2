command="mvn test -Dparallel=$1 -Dbrowser=$2 -Dwebdriver.remote.url=$3 -Dbrowser.version=$4"

echo "Run: " $command
eval $command
echo "End of running: " $command