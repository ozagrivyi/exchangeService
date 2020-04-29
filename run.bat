echo Stop and Remove
docker-compose -f docker-compose.yml down
echo Build
call gradlew.bat clean assemble
echo Run
docker-compose -f docker-compose.yml up --build --force-recreate -d