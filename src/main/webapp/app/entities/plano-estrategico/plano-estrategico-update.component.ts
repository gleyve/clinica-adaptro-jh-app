import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPlanoEstrategico, PlanoEstrategico } from 'app/shared/model/plano-estrategico.model';
import { PlanoEstrategicoService } from './plano-estrategico.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente/cliente.service';

type SelectableEntity = IUser | ICliente;

@Component({
  selector: 'jhi-plano-estrategico-update',
  templateUrl: './plano-estrategico-update.component.html',
})
export class PlanoEstrategicoUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  clientes: ICliente[] = [];
  dataInicioDp: any;
  dataFimDp: any;

  editForm = this.fb.group({
    id: [],
    dataInicio: [],
    dataFim: [],
    detalhamento: [null, [Validators.required, Validators.maxLength(1000)]],
    dataHoraCadastro: [null, [Validators.required]],
    user: [],
    cliente: [null, Validators.required],
  });

  constructor(
    protected planoEstrategicoService: PlanoEstrategicoService,
    protected userService: UserService,
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planoEstrategico }) => {
      if (!planoEstrategico.id) {
        const today = moment().startOf('day');
        planoEstrategico.dataHoraCadastro = today;
      }

      this.updateForm(planoEstrategico);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.clienteService.query().subscribe((res: HttpResponse<ICliente[]>) => (this.clientes = res.body || []));
    });
  }

  updateForm(planoEstrategico: IPlanoEstrategico): void {
    this.editForm.patchValue({
      id: planoEstrategico.id,
      dataInicio: planoEstrategico.dataInicio,
      dataFim: planoEstrategico.dataFim,
      detalhamento: planoEstrategico.detalhamento,
      dataHoraCadastro: planoEstrategico.dataHoraCadastro ? planoEstrategico.dataHoraCadastro.format(DATE_TIME_FORMAT) : null,
      user: planoEstrategico.user,
      cliente: planoEstrategico.cliente,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const planoEstrategico = this.createFromForm();
    if (planoEstrategico.id !== undefined) {
      this.subscribeToSaveResponse(this.planoEstrategicoService.update(planoEstrategico));
    } else {
      this.subscribeToSaveResponse(this.planoEstrategicoService.create(planoEstrategico));
    }
  }

  private createFromForm(): IPlanoEstrategico {
    return {
      ...new PlanoEstrategico(),
      id: this.editForm.get(['id'])!.value,
      dataInicio: this.editForm.get(['dataInicio'])!.value,
      dataFim: this.editForm.get(['dataFim'])!.value,
      detalhamento: this.editForm.get(['detalhamento'])!.value,
      dataHoraCadastro: this.editForm.get(['dataHoraCadastro'])!.value
        ? moment(this.editForm.get(['dataHoraCadastro'])!.value, DATE_TIME_FORMAT)
        : undefined,
      user: this.editForm.get(['user'])!.value,
      cliente: this.editForm.get(['cliente'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanoEstrategico>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
