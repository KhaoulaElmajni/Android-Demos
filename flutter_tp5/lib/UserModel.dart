import 'package:flutter/material.dart';

class UserModel{
  UserModel({
    required this.name,
    required this.gender,
    required this.email,
    required this.phone,
    required this.picture,
  });

  String name;
  String gender;
  String email;
  String phone;
  String picture;

  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
      name: json['name']['first'],
      gender: json['gender'],
      picture: json['picture']['medium'],
      email: json['email'],
      phone: json['phone'],
    );
  }
}

List<UserModel> userList = [];
