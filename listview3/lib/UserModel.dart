import 'package:flutter/material.dart';

class UserModel{
  UserModel({
    required this.name,
    required this.username,
    required this.email,
    required this.phone,
    required this.website,
    required this.imageUrl,
  });

  String name;
  String username;
  String email;
  String phone;
  String website;
  String imageUrl;

  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
      name: json['name'],
      username: json['username'],
      imageUrl: json['image'],
      email: json['email'],
      website: json['website'],
      phone: json['phone'],
    );
  }
}

List<UserModel> userList = [];
