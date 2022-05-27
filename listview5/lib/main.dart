import 'package:flutter/material.dart';

void main() {
  runApp( MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<Widget> _strings = [];

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'USERS',
      home: Scaffold(
        body: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: _strings.length,
                itemBuilder: (context, index) => _strings[index],
              ),
            ),
            ElevatedButton(
              onPressed: () {
                setState(
                      () {
                    _strings.add(
                      Text('new user added'),
                    );
                  },
                );
              },
              child: Text('Add user'),
            )
          ],
        ),
      ),
    );
  }
}