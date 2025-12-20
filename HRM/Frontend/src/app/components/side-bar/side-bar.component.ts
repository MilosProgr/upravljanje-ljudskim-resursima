import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [RouterModule, NgIf, NgClass, CommonModule],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent implements OnInit {

  constructor(private loginService: LoginService,) { }

  ngOnInit(): void {
  }


  proveraUloge(uloga: string): boolean {
    return this.loginService.validateRoles([uloga]);
  }

}
