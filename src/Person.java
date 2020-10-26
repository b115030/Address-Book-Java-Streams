
    public class Person {
        public String firstName, lastName, houseNo, city, state, email;
        public long zip;
        public long phoneNo;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getZip() {
            return zip;
        }

        public void setZip(long zip) {
            this.zip = zip;
        }

        public long getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(long phoneNo) {
            this.phoneNo = phoneNo;
        }

        public Person(String fName, String lName, String address, String city, String state, long zip, long phoneNo,
                      String email) {
            this.firstName = fName;
            this.lastName = lName;
            this.houseNo = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.phoneNo = phoneNo;
            this.email = email;
        }

        @Override
        public String toString() {
            return String.format("Name: " + firstName + " " + lastName + ", Address: " + houseNo + ", City: " + city
                    + ", State: " + state + ", zip " + zip + ", phone no. " + phoneNo + ", EmailId: " + email);
        }
    }

