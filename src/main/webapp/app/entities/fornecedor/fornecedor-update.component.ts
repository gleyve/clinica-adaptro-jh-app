import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFornecedor, Fornecedor } from 'app/shared/model/fornecedor.model';
import { FornecedorService } from './fornecedor.service';
import { MASKS, NgBrazilValidators } from 'ng-brazil';
import emailMask from 'text-mask-addons/dist/emailMask';
import { AdaptroUtilsService } from 'app/adaptro-utils.service.ts';

@Component({
  selector: 'jhi-fornecedor-update',
  templateUrl: './fornecedor-update.component.html',
})
export class FornecedorUpdateComponent implements OnInit {
  isSaving = false;

  public MASKS = MASKS;
  emailMask = emailMask;
  public observacao = '';
  /*   constructor() { 
    this.formFields = {
      estado: [''],
      cpf: ['', [<any>Validators.required, <any>NgBrazilValidators.cpf]],
      cnpj: ['', [<any>Validators.required, <any>NgBrazilValidators.cnpj]],
      rg: ['', [<any>Validators.required, <any>NgBrazilValidators.rg]],
      cep: ['', [<any>Validators.required, <any>NgBrazilValidators.cep]],
      telefone: ['', [<any>Validators.required, <any>NgBrazilValidators.telefone]],
      inscricaoestadual: ['', [<any>Validators.required, <any>NgBrazilValidators.inscricaoestadual(this.estado)]]
      currency: ['', [<any>Validators.required, <any>NgBrazilValidators.currency]],
      time: ['', [<any>Validators.required, <any>NgBrazilValidators.time]],
      placa: ['', [<any>Validators.required, <any>NgBrazilValidators.placa]],
      titulo: ['', [<any>Validators.required, <any>NgBrazilValidators.titulo]]
    };
    this.form = this.fb.group(this.formFields);
  } */

  // angular-input-masks

  public myModel = '';
  public mask = ['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  // CPF: 932.463.073-4
  public cpfMask = [/[1-9]/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  // CNPJ: 17.108.122/0001-07
  public cnpjMask = [/[1-9]/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  // CEP: 60.122-180
  //public cepMask = [/[1-9]/, /\d/, '.', /\d/, /\d/, /\d/, '-', /[1-9]/, /\d/, /\d/]
  public cepMask = [/[1-9]/, /\d/, /\d/, /\d/, /\d/, '-', /[1-9]/, /\d/, /\d/];
  // FONE: '(00) 0000-0000 || (00) 0 0000-0000';
  public phoneBrMask = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public celPhoneBrMask = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, '.', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public mascaraNascimento = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  editForm = this.fb.group({
    id: [],
    tipoPessoa: [null, [Validators.required]],
    // numeroCPF: [null, [Validators.maxLength(14), Validators.pattern('(\\d{3}.)(\\d{3}.)(\\d{3}-)(\\d{2})')]],
    numeroCPF: [null, [NgBrazilValidators.cpf]],
    numeroCNPJ: [null, [NgBrazilValidators.cnpj]],
    numeroInscricaoEstadual: [null, [Validators.maxLength(9)]],
    nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    nomeFantasia: [null, [Validators.maxLength(100)]],
    email: [null, [Validators.maxLength(120), Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    telefone1: [null, [NgBrazilValidators.telefone]],
    telefone2: [null, [NgBrazilValidators.telefone]],
    logradouroNome: [null, [Validators.minLength(2), Validators.maxLength(70)]],
    logradouroNumero: [null, [Validators.maxLength(4)]],
    logradouroComplemento: [null, [Validators.maxLength(50)]],
    bairro: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    cep: [null, [NgBrazilValidators.cep]],
    cidade: [null, [Validators.minLength(2), Validators.maxLength(30)]],
    estado: [],
    observacao: [null, [Validators.maxLength(1000)]],
  });

  constructor(
    protected fornecedorService: FornecedorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private adaptroUtilsService: AdaptroUtilsService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fornecedor }) => {
      this.updateForm(fornecedor);
    });
  }

  updateForm(fornecedor: IFornecedor): void {
    this.editForm.patchValue({
      id: fornecedor.id,
      tipoPessoa: fornecedor.tipoPessoa,
      numeroCPF: fornecedor.numeroCPF,
      numeroCNPJ: fornecedor.numeroCNPJ,
      numeroInscricaoEstadual: fornecedor.numeroInscricaoEstadual,
      nome: fornecedor.nome,
      nomeFantasia: fornecedor.nomeFantasia,
      email: fornecedor.email,
      telefone1: fornecedor.telefone1,
      telefone2: fornecedor.telefone2,
      logradouroNome: fornecedor.logradouroNome,
      logradouroNumero: fornecedor.logradouroNumero,
      logradouroComplemento: fornecedor.logradouroComplemento,
      bairro: fornecedor.bairro,
      cep: fornecedor.cep,
      cidade: fornecedor.cidade,
      estado: fornecedor.estado,
      observacao: fornecedor.observacao,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fornecedor = this.createFromForm();
    if (fornecedor.id !== undefined) {
      this.subscribeToSaveResponse(this.fornecedorService.update(fornecedor));
    } else {
      this.subscribeToSaveResponse(this.fornecedorService.create(fornecedor));
    }
  }

  private createFromForm(): IFornecedor {
    return {
      ...new Fornecedor(),
      id: this.editForm.get(['id'])!.value,
      tipoPessoa: this.editForm.get(['tipoPessoa'])!.value,
      numeroCPF: this.adaptroUtilsService.cleanupMask(this.editForm.get(['numeroCPF'])!.value),
      numeroCNPJ: this.adaptroUtilsService.cleanupMask(this.editForm.get(['numeroCNPJ'])!.value),
      numeroInscricaoEstadual: this.editForm.get(['numeroInscricaoEstadual'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      nomeFantasia: this.editForm.get(['nomeFantasia'])!.value,
      email: this.editForm.get(['email'])!.value,
      telefone1: this.adaptroUtilsService.cleanupMask(this.editForm.get(['telefone1'])!.value),
      telefone2: this.adaptroUtilsService.cleanupMask(this.editForm.get(['telefone2'])!.value),
      logradouroNome: this.editForm.get(['logradouroNome'])!.value,
      logradouroNumero: this.editForm.get(['logradouroNumero'])!.value,
      logradouroComplemento: this.editForm.get(['logradouroComplemento'])!.value,
      bairro: this.editForm.get(['bairro'])!.value,
      cep: this.adaptroUtilsService.cleanupMask(this.editForm.get(['cep'])!.value),
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      observacao: this.editForm.get(['observacao'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFornecedor>>): void {
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
}
