import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPlanoEstrategico } from 'app/shared/model/plano-estrategico.model';

type EntityResponseType = HttpResponse<IPlanoEstrategico>;
type EntityArrayResponseType = HttpResponse<IPlanoEstrategico[]>;

@Injectable({ providedIn: 'root' })
export class PlanoEstrategicoService {
  public resourceUrl = SERVER_API_URL + 'api/plano-estrategicos';

  constructor(protected http: HttpClient) {}

  create(planoEstrategico: IPlanoEstrategico): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(planoEstrategico);
    return this.http
      .post<IPlanoEstrategico>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(planoEstrategico: IPlanoEstrategico): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(planoEstrategico);
    return this.http
      .put<IPlanoEstrategico>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPlanoEstrategico>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPlanoEstrategico[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(planoEstrategico: IPlanoEstrategico): IPlanoEstrategico {
    const copy: IPlanoEstrategico = Object.assign({}, planoEstrategico, {
      dataInicio:
        planoEstrategico.dataInicio && planoEstrategico.dataInicio.isValid() ? planoEstrategico.dataInicio.format(DATE_FORMAT) : undefined,
      dataFim: planoEstrategico.dataFim && planoEstrategico.dataFim.isValid() ? planoEstrategico.dataFim.format(DATE_FORMAT) : undefined,
      dataHoraCadastro:
        planoEstrategico.dataHoraCadastro && planoEstrategico.dataHoraCadastro.isValid()
          ? planoEstrategico.dataHoraCadastro.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataInicio = res.body.dataInicio ? moment(res.body.dataInicio) : undefined;
      res.body.dataFim = res.body.dataFim ? moment(res.body.dataFim) : undefined;
      res.body.dataHoraCadastro = res.body.dataHoraCadastro ? moment(res.body.dataHoraCadastro) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((planoEstrategico: IPlanoEstrategico) => {
        planoEstrategico.dataInicio = planoEstrategico.dataInicio ? moment(planoEstrategico.dataInicio) : undefined;
        planoEstrategico.dataFim = planoEstrategico.dataFim ? moment(planoEstrategico.dataFim) : undefined;
        planoEstrategico.dataHoraCadastro = planoEstrategico.dataHoraCadastro ? moment(planoEstrategico.dataHoraCadastro) : undefined;
      });
    }
    return res;
  }
}
