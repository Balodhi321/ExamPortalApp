import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService,private snack:MatSnackBar) { }

  ngOnInit() {
  }

  public user ={
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };

  formSubmit() {
    console.log(this.user);
    if(this.user.username==''||this.user.password=='')
    {
      // alert('Please fill all the fields');
      this.snack.open("Username and password is required !!", '',{
        duration:2000,
        verticalPosition:'top',
        horizontalPosition:'right',
      });
      return;
    }

    this.userService.addUser(this.user).subscribe(
      (data:any)=>{                             //for success
        console.log(data);
        console.log('this is working');
        // alert('Success');
        Swal.fire('Success !!','UserId is' + data.id,'success');
      },
      (error)=>{                              //for error
        console.log(error);
        // alert('Something went wrong');
        this.snack.open("Something went wrong !!", '',{
          duration:2000,
          verticalPosition:'top',
          horizontalPosition:'right',
        });
      }
    )
  }

}
