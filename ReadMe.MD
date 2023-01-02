***SOLID DESIGN PRINCIPLE***

i> SINGLE RESPONSIBILITY PRINCIPLE:
There should never be more than one reason for a class to change

Let's take an example we have class called AwesomeClass which create and send messege to remote server that is listening
to some port.

                i>communication protocol change
                ii>Message Format change
                iii>Communication Security Added

AWESOME CLASS <-------------------------------------->[SERVER]

What are the possible resons for the class to change?

    Lets say communication protocol changes [http-https]
    Lets say format of the messege change .Before server only accepting JSON format.suddenly it stop
    supporting JSON and accepting only XML formt ,Our class agin could be changed.
    Another reason could be adding authentication on client and server.

There are multiple reason to change our class which should be avoided --says SINGLE REPONSIBILITY. if we have 3 seperate
responsilbility the we should have 3 seperate class or modules to handle those responsibility. In that way whenever
something changes our code ca be changed in organized manner.

In MVC,controller Accepts input and converts it to commands for the model or view.Here controller is performing data
transformation with object mapper and validation logic are also written inside controller class itself.we can say that
controller has multiple responsibility associated with it .if validation logic changes ,controller class also need to be
modified though controller class has nothing to do with validation logic.

SOlUTION: we can create separate Validation service for performing user validations==>UserValidator

Also ,Controller class is also performing persistence operation-User Creation using store i.e in memory data store
directly let's say in future we want to change the way the data is persist i.e jdbc/jpa.again we need to change the
controller class.

SOLUTION: move persistence related logic to Service ==>UserPersistenceService in END package

```java
//Handles incoming JSON requests that work on User resource/entity
public class UserController {
    //Store used by controller
    private Store store = new Store();

    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);
        if (!isValidUser(user)) {
            return "ERROR";
        }
        store.store(user);
        return "SUCCESS";
    }

    //Validates the user object
    private boolean isValidUser(User user) {
        if (!isPresent(user.getName())) {
            return false;
        }
        user.setName(user.getName().trim());

        if (!isValidAlphaNumeric(user.getName())) {
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
            return false;
        }
        user.setEmail(user.getEmail().trim());
        if (!isValidEmail(user.getEmail())) {
            return false;
        }
        return true;
    }

    //Simply checks if value is null or empty..
    private boolean isPresent(String value) {
        return value != null && value.trim().length() > 0;
    }

    //check string for special characters
    private boolean isValidAlphaNumeric(String value) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(value);
        return !matcher.find();
    }

    //check string for valid email address - this is not for prod.
    //Just for demo. This fails for lots of valid emails.
    private boolean isValidEmail(String value) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
```

ii>OPEN CLOSED PRINCIPLE:
Software entities -classes,Modules ,Methods etc. should be open for extension ,but closed for modification.

    open for extension      -->we should be able to extends existing behaviour. 
       
        In OOP,we have base class that is already written and tested.if we want to modifiy the one of the existing method
        present in base class then we should be able to do that.And we can do that using inheritance by overriding that method in
        derived class.    

    closed for modification -->existing code remains unchanged-Whatever already wwritten should not be changed
        
        we should not go and modify base class method which is already written and tested .


eg. if we take a look into any good framework like struts or spring,we will see that we can not change their core logic
& request processing ,but we modify the desired application flow just
by extending some classes and plugin then in configuration files.






