import { AbstractControl, ValidationErrors } from '@angular/forms';

export function checkOption(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (value != 'M' || value != 'Z') {
        return { customUpperCase: true };
    }
    return null;
}

