import { NgFor, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-generic-table',
  standalone: true,
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css'],
  imports: [NgFor, NgIf, MatTableModule, MatIconModule, MatButtonModule]
})
export class GenericTableComponent {

  @Input() tableData: any[] = [];
  @Input() GridArray: any[] = []; // Define GridArray as an input property

  @Output() onEdit = new EventEmitter<any>();
  @Output() onDelete = new EventEmitter<any>();
  @Output() onlogicDelete = new EventEmitter<any>();
  @Output() stat = new EventEmitter<any>();
  @Output() view = new EventEmitter<any>();

  @Input() showViewButton: boolean = true;
  @Input() showUkloniDugme: boolean = true;
  @Input() showIzmeniDugme: boolean = true;
  @Input() prikaziAkcije: boolean = true;


  constructor(public loginService: LoginService) {
  }

  edit(item: any) {
    this.onEdit.emit(item);
  }

  delete(item: any) {
    this.onDelete.emit(item);
  }

  stats(item: any) {
    this.stat.emit(item);
  }

  g_view(item: any) {
    this.view.emit(item);
  }

  get isAdmin(): boolean {
    return this.loginService.validateRoles(['ADMINISTRATOR']);
  }

  get displayedColumns(): string[] {
    const columns = this.tableData.map(header => header.FieldName);
    if (this.isAdmin) {
      columns.push('actions');
    }
    return ['index', ...columns];
  }
}
