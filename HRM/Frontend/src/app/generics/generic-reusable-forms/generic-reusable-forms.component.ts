import { CommonModule, NgFor, NgIf, NgSwitch } from '@angular/common';
import { Component, EventEmitter, forwardRef, Input, Output } from '@angular/core';
import { FormControl, FormGroup, NG_VALUE_ACCESSOR, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatOption } from '@angular/material/core';
import { MatFormField, MatFormFieldControl, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'app-generic-reusable-forms',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor, NgSwitch, CommonModule, MatLabel, MatInput, MatFormField, MatOption, MatButtonModule, MatSelect, NgIf],
  templateUrl: './generic-reusable-forms.component.html',
  styleUrl: './generic-reusable-forms.component.css',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => GenericReusableFormsComponent),
      multi: true
    }
  ]

})
export class GenericReusableFormsComponent {

  @Input() formMetadata: any[] = [];
  @Input() selectOptions: { [key: string]: any[] } = {};
  @Input() initialData: any = null;

  @Output() createEvent = new EventEmitter<any>();
  @Output() pretraziEvent = new EventEmitter<any>();

  @Input() showSearchButton: boolean = true;
  @Input() showResetButton: boolean = true;
  @Input() showAddButton: boolean = true;

  @Input() options: { label: string, value: string }[] = [];  // Accepts options

  @Input() selectedOption: string | undefined;
  @Output() selectionChange = new EventEmitter<string | undefined>();


  forma: FormGroup = new FormGroup({});

  constructor(public loginService: LoginService) { }

  ngOnChanges() {
    if (this.formMetadata.length) {
      this.createFormControls();
    }
  }

  createFormControls(): void {
    const controls: { [key: string]: FormControl } = {};

    this.formMetadata.forEach(field => {
      controls[field.name] = new FormControl(
        this.initialData ? this.initialData[field.name] : null,
        field.required ? Validators.required : []
      );
    });

    // Ensure the 'id' field is included
    controls['id'] = new FormControl(this.initialData ? this.initialData['id'] : null);

    this.forma = new FormGroup(controls);

    if (this.initialData) {
      this.forma.patchValue(this.initialData);
    }
  }

  get isAdmin(): boolean {
    return this.loginService.validateRoles(['ROLE_ADMIN']);
  }


  create(): void {
    if (this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }

  pretrazi(): void {
    if (this.forma.valid) {
      this.pretraziEvent.emit(this.forma.value);
    }
  }

  onSelectionChange(value: string | undefined) {
    this.selectionChange.emit(value);
  }
}
