<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\Enseignant;
use Symfony\Component\Validator\Constraints as Assert;



/**
 * Seance
 *
 * @ORM\Table(name="seance", indexes={@ORM\Index(name="seance_ibfk_1", columns={"idEns"})})
 * @ORM\Entity
 */
class Seance
{
    /**
     * @var int
     *
     * @ORM\Column(name="idSeance", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idseance;

    /**
     * @var string
     *
     * @ORM\Column(name="nomClasse", type="string", length=20, nullable=false)
     *
     */
    private $nomclasse="";

    /**
     * @var string
     *
     * @ORM\Column(name="matiere", type="string", length=20, nullable=false)
     *
     */
    private $matiere="";

    /**
     * @var string
     *
     * @ORM\Column(name="heure", type="string", length=20, nullable=false)
     */
    private $heure="";

    /**
     * @var string
     *
     * @ORM\Column(name="dateSeance", type="string", length=20, nullable=false)
     */
    private $dateseance="";

    /**
     * @var \Enseignant
     * @Assert\NotBlank

     * @ORM\OneToOne(targetEntity="Enseignant")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idEns", referencedColumnName="idEns")
     * })
     */
    private $idens;

    /**
     * @return int
     */
    public function getIdseance(): int
    {
        return $this->idseance;
    }

    /**
     * @param int $idseance
     */
    public function setIdseance(int $idseance): void
    {
        $this->idseance = $idseance;
    }

    /**
     * @return string
     */
    public function getNomclasse(): string
    {
        return $this->nomclasse;
    }

    /**
     * @param string $nomclasse
     */
    public function setNomclasse(string $nomclasse): void
    {
        $this->nomclasse = $nomclasse;
    }

    /**
     * @return string
     */
    public function getMatiere(): string
    {
        return $this->matiere;
    }

    /**
     * @param string $matiere
     */
    public function setMatiere(string $matiere): void
    {
        $this->matiere = $matiere;
    }

    /**
     * @return string
     */
    public function getHeure(): string
    {
        return $this->heure;
    }

    /**
     * @param string $heure
     */
    public function setHeure(string $heure): void
    {
        $this->heure = $heure;
    }

    /**
     * @return string
     */
    public function getDateseance(): string
    {
        return $this->dateseance;
    }

    /**
     * @param string $dateseance
     */
    public function setDateseance(string $dateseance): void
    {
        $this->dateseance = $dateseance;
    }

    /**
     * @return \Enseignant
     */
    public function getIdens(): ?Enseignant
    {
        return $this->idens;
    }

    /**
     * @param \Enseignant $idens
     */
    public function setIdens(Enseignant $idens): self
    {
        $this->idens = $idens;
        return $this;
    }


}
