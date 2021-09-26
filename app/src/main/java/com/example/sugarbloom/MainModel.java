package com.example.sugarbloom;

public class MainModel {

   String  address, email , name ,turl;

   MainModel()
   {


   }

   public MainModel(String address, String email, String name, String turl) {
      this.address = address;
      this.email = email;
      this.name = name;
      this.turl = turl;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTurl() {
      return turl;
   }

   public void setTurl(String turl) {
      this.turl = turl;
   }
}
