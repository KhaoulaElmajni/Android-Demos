import 'dart:async';
import 'dart:convert';


import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:listview3/UserModel.dart';

import 'UserCard.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      home: Home(),
    );
  }
}
class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  Future<List<UserModel>> fetchUser() async {
    userList = [];
    final response = await http.get(Uri.parse(
        'https://jsonplaceholder.typicode.com/users'));

    if (response.statusCode == 200) {
      List<dynamic> values = [];
      values = json.decode(response.body);
      if (values.length > 0) {
        for (int i = 0; i < values.length; i++) {
          if (values[i] != null) {
            Map<String, dynamic> map = values[i];
            userList.add(UserModel.fromJson(map));
          }
        }
        setState(() {
          userList;
        });
      }
      return userList;
    } else {
      throw Exception('Failed to load Users');
    }
  }

  @override
  void initState() {
    fetchUser();
    Timer.periodic(Duration(seconds: 10), (timer) => fetchUser());
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.grey[300],
        appBar: AppBar(
          backgroundColor: Colors.grey[300],
          title: Text(
            'USERS LIST',
            style: TextStyle(
              color: Colors.grey[900],
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
        body: ListView.builder(
          scrollDirection: Axis.vertical,
          itemCount: userList.length,
          itemBuilder: (context, index) {
            return UserCard(
              name: userList[index].name,
              username: userList[index].username,
              imageUrl: userList[index].imageUrl,
              phone: userList[index].phone,
              website: userList[index].website,
              email: userList[index].email,
            );
          },
        ));
  }
}
