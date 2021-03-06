import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IFuncionario, Funcionario } from 'app/shared/model/funcionario.model';
import { FuncionarioService } from './funcionario.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';
import { EspecialidadeSaudeService } from 'app/entities/especialidade-saude/especialidade-saude.service';
import { MASKS, NgBrazilValidators } from 'ng-brazil';
import emailMask from 'text-mask-addons/dist/emailMask';
import createNumberMask from 'text-mask-addons/dist/createNumberMask';

type SelectableEntity = IUser | IEspecialidadeSaude;

const currencyMask = {
  prefix: 'R$ ',
  thousandsSeparatorSymbol: '.',
  decimalSymbol: ',',
  includeThousandsSeparator: true,
  allowDecimal: true,
  decimalLimit: 2,
  requireDecimal: true,
  allowNegative: false,
  allowLeadingZeroes: true,
  integerLimit: 8,
};

const currencyFormatter = new Intl.NumberFormat('pt-BR', {
  style: 'currency',
  currency: 'BRL',
  minimumFractionDigits: 2,
});

@Component({
  selector: 'jhi-funcionario-update',
  templateUrl: './funcionario-update.component.html',
})
export class FuncionarioUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  especialidadesaudes: IEspecialidadeSaude[] = [];
  dataNascimentoDp: any;
  dataAdmissaoDp: any;
  dataDesligamentoDp: any;

  public MASKS = MASKS;
  emailMask = emailMask;

  public observacao = '';

  /*   readonly priceMask = {
    mask: createNumberMask({
      allowDecimal: true,
      decimalSymbol: ',',
      integerLimit: 7,
      prefix: 'R$ ',
      thousandsSeparatorSymbol: '.'
    })
  }; */

  currencyMask = createNumberMask({
    ...currencyMask,
    /*     prefix: 'R$ ',
    postfix: '',
    allowDecimal: true,
    integerLimit: 3,
    maxValue: 10000,
    minValue: 0 */
  });

  editForm = this.fb.group({
    id: [],
    nomeCompleto: [null, [Validators.required, Validators.maxLength(100)]],
    foto: [],
    fotoContentType: [],
    dataNascimento: [],
    numeroConselhoProfissional: [null, [Validators.maxLength(20)]],
    cpf: [null, [NgBrazilValidators.cpf]],
    rg: [null, [Validators.maxLength(15)]],
    cnh: [null, [Validators.maxLength(15)]],
    telefone1: [null, [NgBrazilValidators.telefone]],
    telefone2: [null, [NgBrazilValidators.telefone]],
    email: [null, [Validators.maxLength(120), Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    dataAdmissao: [],
    dataDesligamento: [],
    salario: [],
    sexo: [null, [Validators.required]],
    estadoCivil: [],
    escolaridade: [],
    funcao: [null, [Validators.required]],
    descOutraFuncao: [null, [Validators.maxLength(40)]],
    logradouroNome: [null, [Validators.minLength(2), Validators.maxLength(70)]],
    logradouroNumero: [null, [Validators.maxLength(5)]],
    logradouroComplemento: [null, [Validators.maxLength(50)]],
    bairro: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    proximidadesLogradouro: [null, [Validators.maxLength(50)]],
    cep: [null, [NgBrazilValidators.cep]],
    cidade: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    estado: [],
    dataHoraCadastro: [null, [Validators.required]],
    observacao: [null, [Validators.maxLength(1000)]],
    curriculo: [],
    curriculoContentType: [],
    user: [],
    especialidadeSaude: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected funcionarioService: FuncionarioService,
    protected userService: UserService,
    protected especialidadeSaudeService: EspecialidadeSaudeService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ funcionario }) => {
      if (!funcionario.id) {
        const today = moment().startOf('day');
        funcionario.dataHoraCadastro = today;
      }

      this.updateForm(funcionario);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.especialidadeSaudeService
        .query()
        .subscribe((res: HttpResponse<IEspecialidadeSaude[]>) => (this.especialidadesaudes = res.body || []));
    });
  }

  updateForm(funcionario: IFuncionario): void {
    this.editForm.patchValue({
      id: funcionario.id,
      nomeCompleto: funcionario.nomeCompleto,
      foto: funcionario.foto,
      fotoContentType: funcionario.fotoContentType,
      dataNascimento: funcionario.dataNascimento,
      numeroConselhoProfissional: funcionario.numeroConselhoProfissional,
      cpf: funcionario.cpf,
      rg: funcionario.rg,
      cnh: funcionario.cnh,
      telefone1: funcionario.telefone1,
      telefone2: funcionario.telefone2,
      email: funcionario.email,
      dataAdmissao: funcionario.dataAdmissao,
      dataDesligamento: funcionario.dataDesligamento,
      salario: currencyFormatter.format(funcionario.salario!),
      sexo: funcionario.sexo,
      estadoCivil: funcionario.estadoCivil,
      escolaridade: funcionario.escolaridade,
      funcao: funcionario.funcao,
      descOutraFuncao: funcionario.descOutraFuncao,
      logradouroNome: funcionario.logradouroNome,
      logradouroNumero: funcionario.logradouroNumero,
      logradouroComplemento: funcionario.logradouroComplemento,
      bairro: funcionario.bairro,
      proximidadesLogradouro: funcionario.proximidadesLogradouro,
      cep: funcionario.cep,
      cidade: funcionario.cidade,
      estado: funcionario.estado,
      dataHoraCadastro: funcionario.dataHoraCadastro ? funcionario.dataHoraCadastro.format(DATE_TIME_FORMAT) : null,
      observacao: funcionario.observacao,
      curriculo: funcionario.curriculo,
      curriculoContentType: funcionario.curriculoContentType,
      user: funcionario.user,
      especialidadeSaude: funcionario.especialidadeSaude,
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
    const funcionario = this.createFromForm();
    if (funcionario.id !== undefined) {
      this.subscribeToSaveResponse(this.funcionarioService.update(funcionario));
    } else {
      this.subscribeToSaveResponse(this.funcionarioService.create(funcionario));
    }
  }

  private createFromForm(): IFuncionario {
    return {
      ...new Funcionario(),
      id: this.editForm.get(['id'])!.value,
      nomeCompleto: this.editForm.get(['nomeCompleto'])!.value,
      fotoContentType: this.editForm.get(['fotoContentType'])!.value,
      foto: this.editForm.get(['foto'])!.value,
      dataNascimento: this.editForm.get(['dataNascimento'])!.value,
      numeroConselhoProfissional: this.editForm.get(['numeroConselhoProfissional'])!.value,
      cpf: this.cleanupMask(this.editForm.get(['cpf'])!.value),
      rg: this.cleanupMask(this.editForm.get(['rg'])!.value),
      cnh: this.cleanupMask(this.editForm.get(['cnh'])!.value),
      telefone1: this.cleanupMask(this.editForm.get(['telefone1'])!.value),
      telefone2: this.cleanupMask(this.editForm.get(['telefone2'])!.value),
      email: this.editForm.get(['email'])!.value,
      dataAdmissao: this.editForm.get(['dataAdmissao'])!.value,
      dataDesligamento: this.editForm.get(['dataDesligamento'])!.value,
      salario: this.cleanupCurrencyMask(this.editForm.get(['salario'])!.value),
      sexo: this.editForm.get(['sexo'])!.value,
      estadoCivil: this.editForm.get(['estadoCivil'])!.value,
      escolaridade: this.editForm.get(['escolaridade'])!.value,
      funcao: this.editForm.get(['funcao'])!.value,
      descOutraFuncao: this.editForm.get(['descOutraFuncao'])!.value,
      logradouroNome: this.editForm.get(['logradouroNome'])!.value,
      logradouroNumero: this.editForm.get(['logradouroNumero'])!.value,
      logradouroComplemento: this.editForm.get(['logradouroComplemento'])!.value,
      bairro: this.editForm.get(['bairro'])!.value,
      proximidadesLogradouro: this.editForm.get(['proximidadesLogradouro'])!.value,
      cep: this.cleanupMask(this.editForm.get(['cep'])!.value),
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      dataHoraCadastro: this.editForm.get(['dataHoraCadastro'])!.value
        ? moment(this.editForm.get(['dataHoraCadastro'])!.value, DATE_TIME_FORMAT)
        : undefined,
      observacao: this.editForm.get(['observacao'])!.value,
      curriculoContentType: this.editForm.get(['curriculoContentType'])!.value,
      curriculo: this.editForm.get(['curriculo'])!.value,
      user: this.editForm.get(['user'])!.value,
      especialidadeSaude: this.editForm.get(['especialidadeSaude'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFuncionario>>): void {
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

  cleanupMask(strMaskedData: any): any {
    if (strMaskedData === null || strMaskedData === '' || strMaskedData === undefined) {
      return (strMaskedData = undefined);
    } else return strMaskedData.replace(/\D+/g, '');
  }

  cleanupCurrencyMask(strMaskedData: any): any {
    if (strMaskedData === null || strMaskedData === '' || strMaskedData === undefined) {
      return (strMaskedData = undefined);
    } else return strMaskedData.replace(' ', '').replace('.', '').replace(',', '.').replace('R$', '');
  }

  /*   cleanupMoneyMask(strMaskedData: any): any {
    if (strMaskedData === null || strMaskedData === '' || strMaskedData === undefined) {
      return (strMaskedData = undefined);
    } else return strMaskedData.replace(/\,/gi, '').replace('.', '').replace('R$', '');
  } */
}
