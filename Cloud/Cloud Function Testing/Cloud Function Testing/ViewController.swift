//
//  ViewController.swift
//  Cloud Function Testing
//
//  Created by Aditya Chugh on 8/15/15.
//  Copyright (c) 2015 Mindbend Studio. All rights reserved.
//

import UIKit
import Parse

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        var point = PFGeoPoint(latitude: 43.472449241038724, longitude: -80.5398904204398)
        
        var query = PFQuery(className: "Discoveo")
        query.whereKey("location", nearGeoPoint: point, withinKilometers: 1.0)
        query.findObjectsInBackgroundWithBlock {
            (results, error) -> Void in
            println(results)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

