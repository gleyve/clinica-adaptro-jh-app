import { TipoPessoa } from 'app/shared/model/enumerations/tipo-pessoa.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IFornecedor {
  id?: number;
  tipoPessoa?: TipoPessoa;
  numeroCPF?: string;
  numeroCNPJ?: string;
  numeroInscricaoEstadual?: string;
  nome?: string;
  nomeFantasia?: string;
  email?: string;
  telefone1?: string;
  telefone2?: string;
  logradouroNome?: string;
  logradouroNumero?: string;
  logradouroComplemento?: string;
  bairro?: string;
  cep?: string;
  cidade?: string;
  estado?: Estado;
  observacao?: string;
}

export class Fornecedor implements IFornecedor {
  constructor(
    public id?: number,
    public tipoPessoa?: TipoPessoa,
    public numeroCPF?: string,
    public numeroCNPJ?: string,
    public numeroInscricaoEstadual?: string,
    public nome?: string,
    public nomeFantasia?: string,
    public email?: string,
    public telefone1?: string,
    public telefone2?: string,
    public logradouroNome?: string,
    public logradouroNumero?: string,
    public logradouroComplemento?: string,
    public bairro?: string,
    public cep?: string,
    public cidade?: string,
    public estado?: Estado,
    public observacao?: string
  ) {}
}
