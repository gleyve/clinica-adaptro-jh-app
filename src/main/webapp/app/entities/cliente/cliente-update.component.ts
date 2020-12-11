import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICliente, Cliente } from 'app/shared/model/cliente.model';
import { ClienteService } from './cliente.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-cliente-update',
  templateUrl: './cliente-update.component.html',
})
export class ClienteUpdateComponent implements OnInit {
  isSaving = false;
  clientes: ICliente[] = [];
  dataNascimentoDp: any;
  dataValidadeConvenioDp: any;

  editForm = this.fb.group({
    id: [],
    tipoCliente: [null, [Validators.required]],
    matricula: [null, [Validators.minLength(6), Validators.maxLength(6)]],
    nomeCompleto: [null, [Validators.required, Validators.maxLength(100)]],
    foto: [null, []],
    fotoContentType: [],
    dataNascimento: [],
    sexo: [null, [Validators.required]],
    estadoCivil: [],
    escolaridade: [],
    convenio: [null, [Validators.required]],
    numCarteirinhaConvenio: [null, [Validators.maxLength(20)]],
    dataValidadeConvenio: [],
    procedencia: [],
    profissao: [null, [Validators.minLength(2), Validators.maxLength(70)]],
    cpf: [null, [Validators.maxLength(11), Validators.pattern('(\\d{3})(\\d{3})(\\d{3})(\\d{2})')]],
    rg: [null, [Validators.maxLength(15)]],
    telefone1: [null, [Validators.maxLength(15), Validators.pattern('^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$')]],
    telefone2: [null, [Validators.maxLength(15), Validators.pattern('^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$')]],
    email: [null, [Validators.maxLength(120), Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    logradouroNome: [null, [Validators.minLength(2), Validators.maxLength(70)]],
    logradouroNumero: [null, [Validators.maxLength(5)]],
    logradouroComplemento: [null, [Validators.maxLength(50)]],
    bairro: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    cep: [null, [Validators.maxLength(9), Validators.pattern('^[0-9]{5}-[0-9]{3}$')]],
    cidade: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    estado: [],
    parentescoResponsavel: [],
    parentescoResponsavelFinanceiro: [],
    dataHoraCadastro: [null, [Validators.required]],
    ativo: [],
    observacao: [null, [Validators.maxLength(1000)]],
    responsavel: [],
    responsavelFinanceiro: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected clienteService: ClienteService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cliente }) => {
      if (!cliente.id) {
        const today = moment().startOf('day');
        cliente.dataHoraCadastro = today;
      }

      this.updateForm(cliente);

      this.clienteService.query().subscribe((res: HttpResponse<ICliente[]>) => (this.clientes = res.body || []));
    });
  }

  updateForm(cliente: ICliente): void {
    this.editForm.patchValue({
      id: cliente.id,
      tipoCliente: cliente.tipoCliente,
      matricula: cliente.matricula,
      nomeCompleto: cliente.nomeCompleto,
      foto: cliente.foto,
      fotoContentType: cliente.fotoContentType,
      dataNascimento: cliente.dataNascimento,
      sexo: cliente.sexo,
      estadoCivil: cliente.estadoCivil,
      escolaridade: cliente.escolaridade,
      convenio: cliente.convenio,
      numCarteirinhaConvenio: cliente.numCarteirinhaConvenio,
      dataValidadeConvenio: cliente.dataValidadeConvenio,
      procedencia: cliente.procedencia,
      profissao: cliente.profissao,
      cpf: cliente.cpf,
      rg: cliente.rg,
      telefone1: cliente.telefone1,
      telefone2: cliente.telefone2,
      email: cliente.email,
      logradouroNome: cliente.logradouroNome,
      logradouroNumero: cliente.logradouroNumero,
      logradouroComplemento: cliente.logradouroComplemento,
      bairro: cliente.bairro,
      cep: cliente.cep,
      cidade: cliente.cidade,
      estado: cliente.estado,
      parentescoResponsavel: cliente.parentescoResponsavel,
      parentescoResponsavelFinanceiro: cliente.parentescoResponsavelFinanceiro,
      dataHoraCadastro: cliente.dataHoraCadastro ? cliente.dataHoraCadastro.format(DATE_TIME_FORMAT) : null,
      ativo: cliente.ativo,
      observacao: cliente.observacao,
      responsavel: cliente.responsavel,
      responsavelFinanceiro: cliente.responsavelFinanceiro,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('clinicaAdaptrojhApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cliente = this.createFromForm();
    if (cliente.id !== undefined) {
      this.subscribeToSaveResponse(this.clienteService.update(cliente));
    } else {
      this.subscribeToSaveResponse(this.clienteService.create(cliente));
    }
  }

  private createFromForm(): ICliente {
    return {
      ...new Cliente(),
      id: this.editForm.get(['id'])!.value,
      tipoCliente: this.editForm.get(['tipoCliente'])!.value,
      matricula: this.editForm.get(['matricula'])!.value,
      nomeCompleto: this.editForm.get(['nomeCompleto'])!.value,
      fotoContentType: this.editForm.get(['fotoContentType'])!.value,
      foto: this.editForm.get(['foto'])!.value,
      dataNascimento: this.editForm.get(['dataNascimento'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      estadoCivil: this.editForm.get(['estadoCivil'])!.value,
      escolaridade: this.editForm.get(['escolaridade'])!.value,
      convenio: this.editForm.get(['convenio'])!.value,
      numCarteirinhaConvenio: this.editForm.get(['numCarteirinhaConvenio'])!.value,
      dataValidadeConvenio: this.editForm.get(['dataValidadeConvenio'])!.value,
      procedencia: this.editForm.get(['procedencia'])!.value,
      profissao: this.editForm.get(['profissao'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      rg: this.editForm.get(['rg'])!.value,
      telefone1: this.editForm.get(['telefone1'])!.value,
      telefone2: this.editForm.get(['telefone2'])!.value,
      email: this.editForm.get(['email'])!.value,
      logradouroNome: this.editForm.get(['logradouroNome'])!.value,
      logradouroNumero: this.editForm.get(['logradouroNumero'])!.value,
      logradouroComplemento: this.editForm.get(['logradouroComplemento'])!.value,
      bairro: this.editForm.get(['bairro'])!.value,
      cep: this.editForm.get(['cep'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      parentescoResponsavel: this.editForm.get(['parentescoResponsavel'])!.value,
      parentescoResponsavelFinanceiro: this.editForm.get(['parentescoResponsavelFinanceiro'])!.value,
      dataHoraCadastro: this.editForm.get(['dataHoraCadastro'])!.value
        ? moment(this.editForm.get(['dataHoraCadastro'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ativo: this.editForm.get(['ativo'])!.value,
      observacao: this.editForm.get(['observacao'])!.value,
      responsavel: this.editForm.get(['responsavel'])!.value,
      responsavelFinanceiro: this.editForm.get(['responsavelFinanceiro'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICliente>>): void {
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

  trackById(index: number, item: ICliente): any {
    return item.id;
  }
}
