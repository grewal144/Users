# Rest API for users

####A user should have at least the following attributes:
```
family name
given name
birthdate
email
```
####A user's permission should have at least the following attributes:
```
type
granted date
```
The API should provide the endpoints to satisfy at least the following functional requirements:
```
list users
add user
remove user
get user by id
grant permission for a user
revoke permission for a user
search users by family name
```

###Build project
```
./gradlew assemble
```

###Run project
```
windows:  java -cp "build/libs/*" com.marinerinnovations.users.UsersApplication
Linux:  java -cp 'build/libs/*' com.marinerinnovations.users.UsersApplication
```


###Resorces to access

####list users -
```
list users - GET localhost:8080/api/users 
```
####add user - 
```
POST localhost:8080/api/user
{
    "birthdate": "2012-10-01T09:45:00",
    "familyName": "Tai",
    "givenName": "Jojo",
    "email": "abc@gmail.com"
}
```
####remove user
```
DELETE localhost:8080/api/user/{userid}
```
####get user by id
```
GET localhost:8080/api/user/{userid}
```
####grant permission for a user
```
POST localhost:8080/api/user/{userid}/permission

{
    "type": "admin",
    "grantedOn": "2012-10-01T09:45:00" 
}
```
####revoke permission for a user
```
DELETE localhost:8080/api/user/{userid}/permission/{permisionid}
```
####search users by family name
```
GET localhost:8080/api/users/Tai
```
