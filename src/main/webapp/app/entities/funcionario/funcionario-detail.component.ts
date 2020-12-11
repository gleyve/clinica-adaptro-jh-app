import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFuncionario } from 'app/shared/model/funcionario.model';

@Component({
  selector: 'jhi-funcionario-detail',
  templateUrl: './funcionario-detail.component.html',
})
export class FuncionarioDetailComponent implements OnInit {
  funcionario: IFuncionario | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ funcionario }) => (this.funcionario = funcionario));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
