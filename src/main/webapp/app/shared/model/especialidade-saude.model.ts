export interface IEspecialidadeSaude {
  id?: number;
  descricao?: string;
  sigla?: string;
  ativo?: boolean;
}

export class EspecialidadeSaude implements IEspecialidadeSaude {
  constructor(public id?: number, public descricao?: string, public sigla?: string, public ativo?: boolean) {
    this.ativo = this.ativo || false;
  }
}
