import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { PayrollRequest, PayrollResult } from '../model/payroll';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PayrollService extends CrudService<PayrollRequest> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/v2/publish`);
  }

  requestPayroll(payload: PayrollRequest): Observable<PayrollResult> {
    return this.client.post<PayrollResult>('/api/v2/payroll', payload);
  }
}
