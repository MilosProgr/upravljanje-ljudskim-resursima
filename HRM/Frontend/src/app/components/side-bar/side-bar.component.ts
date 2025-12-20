import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [RouterModule, NgIf, NgClass, CommonModule],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router,) { }

  ngOnInit(): void {
  }


  proveraUloge(uloga: string): boolean {
    return this.loginService.validateRoles([uloga]);
  }

}
