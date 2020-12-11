import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

type EntityResponseType = HttpResponse<IEspecialidadeSaude>;
type EntityArrayResponseType = HttpResponse<IEspecialidadeSaude[]>;

@Injectable({ providedIn: 'root' })
export class EspecialidadeSaudeService {
  public resourceUrl = SERVER_API_URL + 'api/especialidade-saudes';

  constructor(protected http: HttpClient) {}

  create(especialidadeSaude: IEspecialidadeSaude): Observable<EntityResponseType> {
    return this.http.post<IEspecialidadeSaude>(this.resourceUrl, especialidadeSaude, { observe: 'response' });
  }

  update(especialidadeSaude: IEspecialidadeSaude): Observable<EntityResponseType> {
    return this.http.put<IEspecialidadeSaude>(this.resourceUrl, especialidadeSaude, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEspecialidadeSaude>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEspecialidadeSaude[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
